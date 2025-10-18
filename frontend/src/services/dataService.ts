import api from './api';
import { Tournament, Division, Team, Venue, Match } from '../types';

export const tournamentService = {
  getAll: async (): Promise<Tournament[]> => {
    const response = await api.get('/tournaments');
    return response.data;
  },

  getById: async (id: number): Promise<Tournament> => {
    const response = await api.get(`/tournaments/${id}`);
    return response.data;
  },

  create: async (tournament: Tournament): Promise<Tournament> => {
    const response = await api.post('/tournaments', tournament);
    return response.data;
  },

  update: async (id: number, tournament: Tournament): Promise<Tournament> => {
    const response = await api.put(`/tournaments/${id}`, tournament);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/tournaments/${id}`);
  },
};

export const divisionService = {
  getAll: async (): Promise<Division[]> => {
    const response = await api.get('/divisions');
    return response.data;
  },

  getByTournamentId: async (tournamentId: number): Promise<Division[]> => {
    const response = await api.get(`/tournaments/${tournamentId}/divisions`);
    return response.data;
  },

  getById: async (id: number): Promise<Division> => {
    const response = await api.get(`/divisions/${id}`);
    return response.data;
  },

  create: async (tournamentId: number, division: Division): Promise<Division> => {
    const response = await api.post(`/tournaments/${tournamentId}/divisions`, division);
    return response.data;
  },

  update: async (id: number, division: Division): Promise<Division> => {
    const response = await api.put(`/divisions/${id}`, division);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/divisions/${id}`);
  },

  generateSchedule: async (id: number, startTime: string): Promise<Match[]> => {
    const response = await api.post(`/divisions/${id}/generate-schedule?startTime=${startTime}`);
    return response.data;
  },
};

export const teamService = {
  getAll: async (): Promise<Team[]> => {
    const response = await api.get('/teams');
    return response.data;
  },

  getByDivisionId: async (divisionId: number): Promise<Team[]> => {
    const response = await api.get(`/divisions/${divisionId}/teams`);
    return response.data;
  },

  getById: async (id: number): Promise<Team> => {
    const response = await api.get(`/teams/${id}`);
    return response.data;
  },

  create: async (divisionId: number, team: Team): Promise<Team> => {
    const response = await api.post(`/divisions/${divisionId}/teams`, team);
    return response.data;
  },

  update: async (id: number, team: Team): Promise<Team> => {
    const response = await api.put(`/teams/${id}`, team);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/teams/${id}`);
  },
};

export const venueService = {
  getAll: async (): Promise<Venue[]> => {
    const response = await api.get('/venues');
    return response.data;
  },

  getById: async (id: number): Promise<Venue> => {
    const response = await api.get(`/venues/${id}`);
    return response.data;
  },

  create: async (venue: Venue): Promise<Venue> => {
    const response = await api.post('/venues', venue);
    return response.data;
  },

  update: async (id: number, venue: Venue): Promise<Venue> => {
    const response = await api.put(`/venues/${id}`, venue);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/venues/${id}`);
  },
};

export const matchService = {
  getAll: async (): Promise<Match[]> => {
    const response = await api.get('/matches');
    return response.data;
  },

  getByDivisionId: async (divisionId: number): Promise<Match[]> => {
    const response = await api.get(`/divisions/${divisionId}/matches`);
    return response.data;
  },

  getById: async (id: number): Promise<Match> => {
    const response = await api.get(`/matches/${id}`);
    return response.data;
  },

  create: async (match: Match): Promise<Match> => {
    const response = await api.post('/matches', match);
    return response.data;
  },

  update: async (id: number, match: Match): Promise<Match> => {
    const response = await api.put(`/matches/${id}`, match);
    return response.data;
  },

  updateScore: async (id: number, team1Score: number, team2Score: number): Promise<Match> => {
    const response = await api.put(`/matches/${id}/score`, { team1Score, team2Score });
    return response.data;
  },

  completeMatch: async (id: number): Promise<Match> => {
    const response = await api.put(`/matches/${id}/complete`);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/matches/${id}`);
  },
};
