import React, { useState } from 'react';

const AddBook = () => {
  const [bookName, setBookName] = useState('');
  const [isbn, setIsbn] = useState('');

  // Function to handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent default form submission

    // Prepare the data to be sent to the server
    const bookData = {
      bookName,
      isbn
    };

    // Send a POST request to the '/add' endpoint
    try {
      const response = await fetch('/Book_mgmt/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(bookData) // Send the book data as a JSON string
      });

      if (response.ok) {
        const result = await response.json();
        console.log('Book added successfully:', result);
        // Optionally, clear the form fields after successful submission
        setBookName('');
        setIsbn('');
      } else {
        console.error('Error adding book:', response.status);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <>
      <form className="container form-inline bookarea" onSubmit={handleSubmit}>
        <div className="row">
          <div className="col-sm">
            <div className="form-group mb-2">
              <label htmlFor="bookName" className="sr-only"></label>
              <center>
                <input
                  type="text"
                  className="form-control"
                  id="bookName"
                  placeholder="Book Name"
                  value={bookName}
                  onChange={(e) => setBookName(e.target.value)} // Update state on input change
                />
              </center>
            </div>
          </div>
          <div className="col-sm">
            <div className="form-group mx-sm-3 mb-2">
              <label htmlFor="isbn" className="sr-only"></label>
              <center>
                <input
                  type="text"
                  className="form-control"
                  id="isbn"
                  placeholder="ISBN"
                  value={isbn}
                  onChange={(e) => setIsbn(e.target.value)} // Update state on input change
                />
              </center>
            </div>
          </div>
          <div className="col-sm">
            <center>
              <button type="submit" className="btn btn-success mb-2">
                Add
              </button>
            </center>
          </div>
        </div>
      </form>
    </>
  );
};

export default AddBook;
