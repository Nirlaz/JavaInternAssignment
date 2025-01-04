import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import SideBar from './components/SideBar';
import CreatePost from './components/CreatePost';
import PostList from './components/PostList';
import { useState, useEffect } from 'react';
import { BooksItemsContext } from './store/books-store';

function App() {
  const [selectedTab, setSelectTab] = useState('Home');
  const [book, setBooks] = useState([]);

  // Function to fetch books
  const fetchBooks = async () => {
    try {
      const response = await fetch('/Book_mgmt/books');
      if (response.ok) {
        const data = await response.json();
        setBooks(data); // Update state with the new list
      } else {
        console.error('Error fetching books:', response.status);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  // Fetch books on component mount
  useEffect(() => {
    fetchBooks();
  }, []);

  // Function to delete a book
  const deletebook = async (bookId) => {
    const idData = { id: bookId }; // Use the book's ID

    try {
      const response = await fetch('/Book_mgmt/delete', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(idData), // Send ID data as JSON
      });

      if (response.ok) {
        console.log('Book deleted successfully');
        fetchBooks(); // Refresh the book list
      } else {
        console.error('Error deleting book:', response.status);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <BooksItemsContext.Provider value={{ book, deletebook }}>
      <div className="app-container">
        <SideBar selectedTab={selectedTab} setSelectTab={setSelectTab} />
        <div className="content">
          <Navbar />
          {selectedTab === 'Home' ? <PostList /> : <CreatePost />}
          <Footer />
        </div>
      </div>
    </BooksItemsContext.Provider>
  );
}

export default App;
