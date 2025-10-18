import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Tournament } from '../types';
import { tournamentService } from '../services/dataService';
import './Dashboard.css';

const Dashboard: React.FC = () => {
  const [tournaments, setTournaments] = useState<Tournament[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadTournaments();
  }, []);

  const loadTournaments = async () => {
    try {
      const data = await tournamentService.getAll();
      setTournaments(data);
    } catch (error) {
      console.error('Error loading tournaments:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: number) => {
    if (window.confirm('Are you sure you want to delete this tournament?')) {
      try {
        await tournamentService.delete(id);
        loadTournaments();
      } catch (error) {
        console.error('Error deleting tournament:', error);
      }
    }
  };

  if (loading) return <div className="loading">Loading...</div>;

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h1>Tournaments</h1>
        <Link to="/tournaments/new" className="btn-primary">Create Tournament</Link>
      </div>
      
      <div className="tournament-grid">
        {tournaments.length === 0 ? (
          <div className="empty-state">
            <p>No tournaments yet. Create your first tournament!</p>
          </div>
        ) : (
          tournaments.map((tournament) => (
            <div key={tournament.id} className="tournament-card">
              <div className="tournament-header">
                <h3>{tournament.name}</h3>
                <span className={`status-badge status-${tournament.status.toLowerCase()}`}>
                  {tournament.status}
                </span>
              </div>
              <p className="tournament-description">{tournament.description}</p>
              <div className="tournament-dates">
                <div>Start: {new Date(tournament.startDate).toLocaleDateString()}</div>
                <div>End: {new Date(tournament.endDate).toLocaleDateString()}</div>
              </div>
              <div className="tournament-actions">
                <Link to={`/tournaments/${tournament.id}`} className="btn-secondary">View</Link>
                <Link to={`/tournaments/${tournament.id}/edit`} className="btn-secondary">Edit</Link>
                <button onClick={() => handleDelete(tournament.id!)} className="btn-danger">Delete</button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Dashboard;
