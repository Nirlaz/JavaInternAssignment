import React, { useState } from 'react';
import PostList from './PostList';
import { useContext } from 'react';
import { BooksItemsContext } from '../store/books-store';

const Post = ({book}) => {

 const {deletebook}= useContext(BooksItemsContext);

  return (
    <>
      <div className="container bookarea">
        <div className="row">
          <div className="col-sm">
            <center><h6>{book.id}</h6></center>
          </div>
          <div className="col-sm">
            <center><h6>{book.bookName}</h6></center>
          </div>
          <div className="col-sm">
            <center><h6>{book.isbn}</h6></center>
          </div>
          <div className="col-sm">
            <center>
              <button type="button"
                className="btn btn-danger"
                onClick={()=>deletebook(book.id)} // Trigger delete on button click
              >
                Delete
              </button>
            </center>
          </div>
        </div>
      </div>
    </>
  );
};

export default Post;
