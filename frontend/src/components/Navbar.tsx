import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Navbar.css';

const Navbar: React.FC = () => {
  const { user, logout, isAuthenticated } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  if (!isAuthenticated) return null;

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="navbar-brand">VORIN</Link>
        <div className="navbar-links">
          <Link to="/">Tournaments</Link>
          <Link to="/teams">Teams</Link>
          <Link to="/venues">Venues</Link>
          <Link to="/matches">Matches</Link>
        </div>
        <div className="navbar-user">
          <span className="user-name">{user?.username}</span>
          <button onClick={handleLogout} className="btn-logout">Logout</button>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
