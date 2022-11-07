import React from 'react';
import MainRoutes from './routes/MainRoutes';
import Menu from './components/Menu';

export default function App() {
  return (
    <div>
        <Menu />
        <MainRoutes />
    </div>
  );
}