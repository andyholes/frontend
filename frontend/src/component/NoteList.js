import Note from "./Note";
import React, { useState, useEffect } from "react";
import axios from "axios";
import './NoteList.css';

function NotesList() {
    const [notes, setNotes] = useState([]);
    const [isArchived, setArchived] = useState(false);

    const getUrl = `http://localhost:8080/notes`;

    useEffect(() => {
        axios
        .get(getUrl)
        .then((response) => setNotes(response.data))
        .catch((error) => console.log(error))
    ;}, [getUrl]);

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
        console.log("notelist: "+newNote);
        const noteIndex = notes.findIndex((note) => note.id === newNote.id);
        const updatedList = [...notes];
        updatedList[noteIndex] = {...newNote, archived: updatedList[noteIndex].archived};
        setNotes(updatedList);
        console.log("A note has been added to the list");
    };

    const showArchived = () => {
        setArchived(!isArchived);
    };

    return (
    <>
    <div className="main-title">
        <h1>My notes</h1>
        <button className="create-button">Create note</button>
        <button className="toggle-archived" onClick={showArchived}>{isArchived ? 
        '< Go back to unarchived notes' :
        'Archived notes'}</button>
    </div>
    <div className="note-list">
        {notes
        .filter((note) => note.archived === isArchived)
        .map((note) => (
        <Note key={note.id} note={note} updateList={updateList} updateNote={updateNote} updateArchived={updateArchived}/>
        ))}
    </div>
    </>
    );
}

export default NotesList;