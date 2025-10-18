import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { ScoreUpdate } from '../types';

class WebSocketService {
  private client: Client | null = null;
  private subscribers: Map<string, ((message: ScoreUpdate) => void)[]> = new Map();

  connect(onConnect?: () => void) {
    this.client = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        console.log('Connected to WebSocket');
        this.subscribeToScores();
        if (onConnect) onConnect();
      },
      onStompError: (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
      },
    });

    this.client.activate();
  }

  disconnect() {
    if (this.client) {
      this.client.deactivate();
    }
  }

  private subscribeToScores() {
    if (this.client) {
      this.client.subscribe('/topic/scores', (message) => {
        const scoreUpdate: ScoreUpdate = JSON.parse(message.body);
        this.notifySubscribers('scores', scoreUpdate);
      });
    }
  }

  onScoreUpdate(callback: (message: ScoreUpdate) => void) {
    if (!this.subscribers.has('scores')) {
      this.subscribers.set('scores', []);
    }
    this.subscribers.get('scores')!.push(callback);
  }

  private notifySubscribers(topic: string, message: ScoreUpdate) {
    const callbacks = this.subscribers.get(topic);
    if (callbacks) {
      callbacks.forEach((callback) => callback(message));
    }
  }
}

export const webSocketService = new WebSocketService();
