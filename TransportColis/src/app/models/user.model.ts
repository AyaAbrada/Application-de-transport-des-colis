export interface User {
  fullName: string;
  username: string;
  password: string;
  role: 'ADMIN' | 'EXPEDITEUR' | 'CONDUCTEUR';
}
