import Note from "./Note";
import React, { useState, useEffect } from "react";
import axios from "axios";
import './NoteList.css';
import NoteForm from "./NoteForm";

function NotesList() {
    const [notes, setNotes] = useState([]);
    const [isArchived, setArchived] = useState(false);
    const [showForm, setShowForm] = useState(false);

    const baseUrl = `http://localhost:8080/notes`;

    useEffect(() => {
        axios
        .get(baseUrl)
        .then((response) => setNotes(response.data))
        .catch((error) => console.log(error))
    ;}, [baseUrl]);

    const cancelForm = () => setShowForm(false);

    const handleCreation = () => {
        setShowForm(true)
    };

    const confirmCreation = (newNote) => {
        axios
        .post(baseUrl, newNote)
        .then((response) => {
          console.log("Created successfully");
          addToList(response.data);
          })
          .catch((error) => console.log(error))
          setShowForm(false);
    };

    const addToList = (note) => {
        setNotes((prevNotes) => [...prevNotes, note]);
    };

    const updateList = (id) => {
        setNotes((notes) => notes.filter((note) => note.id !== id));
        console.log("A note was removed from the list");
    };

    const updateArchived = (id) =>{
        const noteIndex = notes.findIndex((note) => note.id === id);
        const updatedList = [...notes];
        updatedList[noteIndex].archived = !updatedList[noteIndex].archived;
        setNotes(updatedList);
    };

    const updateNote = (newNote) => {
        const noteIndex = notes.findIndex((note) => note.id === newNote.id);
        const updatedList = [...notes];
        updatedList[noteIndex] = newNote;
        setNotes(updatedList);
        console.log("A note has been added to the list");
    };

    const showArchived = () => {
        setArchived(!isArchived);
    };

    const notePlaceholder = {id:0, title:'', content:'', archived: false, tags:[]}

    return (
    <>
    <div className="main-title">
        <h1>My notes</h1>
        <button className="create-button" onClick={handleCreation}>Create note</button>
        <button className="toggle-archived" onClick={showArchived}>{isArchived ? 
        '< Go back to unarchived notes' :
        'Archived notes'}</button>
    </div>
    <div className="note-list">
        {notes
        .filter((note) => note.archived === isArchived)
        .map((note) => (
        <Note key={note.id} 
        note={note} 
        updateList={updateList} 
        updateNote={updateNote} 
        updateArchived={updateArchived}/>
        ))}

        {showForm && 
        <NoteForm note={notePlaceholder}
        onConfirm={confirmCreation} 
        onCancel={cancelForm}/>}
    </div>
    </>
    );
}

export default NotesList;