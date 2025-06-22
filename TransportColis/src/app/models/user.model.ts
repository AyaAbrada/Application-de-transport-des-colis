export interface LoginResponse {
  token: string;
  role: 'ADMIN' | 'EXPEDITEUR' | 'CONDUCTEUR';
}

export interface User {
  fullName: string;
  username: string;
  password: string;
  role: 'ADMIN' | 'EXPEDITEUR' | 'CONDUCTEUR';
}

export interface LoginRequest {
  username: string;
  password: string;
}
