import './App.css';
import NotesList from './component/NoteList';
import React, { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [notes, setNotes] = useState([]);

  const getUrl = `http://localhost:8080/notes`;
  
  useEffect(() => {
    axios
      .get(getUrl)
      .then((response) => setNotes(response.data))
      .catch((error) => console.log(error));
  }, [getUrl]);

  const updateList = (id) => {
    setNotes((notes) => notes.filter((note) => note.id !== id));
    console.log("updateList ejecutado");
  };

  return (
    <NotesList notes={notes} updateList={updateList}/>
  );
}

export default App;
