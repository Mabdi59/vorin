export interface User {
  id: number;
  username: string;
  email: string;
  roles: string[];
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface SignupRequest {
  username: string;
  email: string;
  password: string;
}

export interface JwtResponse {
  token: string;
  type: string;
  id: number;
  username: string;
  email: string;
  roles: string[];
}

export enum TournamentStatus {
  UPCOMING = 'UPCOMING',
  IN_PROGRESS = 'IN_PROGRESS',
  COMPLETED = 'COMPLETED',
  CANCELLED = 'CANCELLED'
}

export interface Tournament {
  id?: number;
  name: string;
  description: string;
  startDate: string;
  endDate: string;
  status: TournamentStatus;
  createdBy?: User;
  createdAt?: string;
}

export enum BracketType {
  ROUND_ROBIN = 'ROUND_ROBIN',
  SINGLE_ELIMINATION = 'SINGLE_ELIMINATION',
  DOUBLE_ELIMINATION = 'DOUBLE_ELIMINATION'
}

export interface Division {
  id?: number;
  name: string;
  description: string;
  bracketType: BracketType;
  tournamentId?: number;
}

export interface Team {
  id?: number;
  name: string;
  description: string;
  divisionId?: number;
  wins: number;
  losses: number;
  draws: number;
  points: number;
}

export interface Venue {
  id?: number;
  name: string;
  address: string;
  capacity: number;
  facilities: string;
}

export enum MatchStatus {
  SCHEDULED = 'SCHEDULED',
  IN_PROGRESS = 'IN_PROGRESS',
  COMPLETED = 'COMPLETED',
  CANCELLED = 'CANCELLED'
}

export interface Match {
  id?: number;
  divisionId?: number;
  team1: Team;
  team2: Team;
  venue?: Venue;
  scheduledTime: string;
  team1Score: number;
  team2Score: number;
  status: MatchStatus;
  roundNumber: number;
  bracketPosition?: number;
}

export interface ScoreUpdate {
  matchId: number;
  team1Score: number;
  team2Score: number;
  status: string;
}
