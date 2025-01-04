import { useContext } from "react";
import { BooksItemsContext } from "../store/books-store";
import Post from "./Post";
import PostTitle from "./PostTilte";
import { useState,useEffect } from "react";

const PostList=()=>{

const {book} = useContext(BooksItemsContext);
/* { status: 'ok', method: 'GET' } */
  // const book=[{"isbn":"9781","id":"55","bookName":"The Girl Who Lived"},{"isbn":"9781","id":"35","bookName":"The Girl Who Lived"},];
  
  return(
    <>
    
      <PostTitle/>
     {book.map((books)=><Post book={books} key={books.id}/>) }
      
    </>
  );
}
export default PostList;