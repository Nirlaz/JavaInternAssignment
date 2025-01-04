import { createContext }  from "react";
export const BooksItemsContext= createContext({
  book:[],
  deletebook:()=>{}
});
