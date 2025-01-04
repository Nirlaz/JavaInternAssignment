import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/Book_mgmt': {
        target: 'http://localhost:8088', // Backend server
        changeOrigin: true, // Adjust the origin of the request
        rewrite: (path) => path.replace(/^\/Book_mgmt/, '/Book_mgmt'), // Optional rewrite
      },
    },
  },
});
