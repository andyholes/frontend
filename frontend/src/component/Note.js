import axios from "axios";
import React, { useState } from "react";
import Dialog from "./Dialog";
import NoteForm from "./NoteForm";
import './Note.css';

function Note({note, updateList, updateNote, updateArchived}) {
  const [showForm, setShowForm] = useState(false);
  const [showDeleteDialog, setShowDeleteDialog] = useState(false);
  
  const deleteAndPutUrl = `https://notes-app-qdc5.onrender.com/notes/${note.id}`;
  const archiveUrl = `https://notes-app-qdc5.onrender.com/notes/toggle-archived/${note.id}`;

    const handleForm = () => setShowForm(true);
    const handleDelete = () => setShowDeleteDialog(true);

    const cancelForm = () => setShowForm(false);
    const cancelDelete = () => setShowDeleteDialog(false);

    const handleArchive = () => {
        updateArchived(note.id);
        axios
          .post(archiveUrl)
          .then((response) => {
          note.archived ? console.log("Unarchived successfully") :
          console.log("Archived successfully");
          })
          .catch((error) => console.log(error))
    };

    const confirmForm = (newNote) => {
        axios
          .put(deleteAndPutUrl, newNote)
          .then((response) => {
          console.log("Edited successfully");
          updateNote(response.data);
          })
          .catch((error) => console.log(error))
          setShowForm(false);
    };

    const confirmDelete = () => {
        updateList(note.id);
        axios
        .delete(deleteAndPutUrl)
        .then((response) => {
        console.log("Deleted successfully");
        })
        .catch((error) => console.log(error))
        setShowDeleteDialog(false);
    };

    const noteImage = "https://www.shareicon.net/data/512x512/2016/11/14/851928_note_512x512.png";
    const unarchivedImage = "https://www.shareicon.net/data/512x512/2015/08/17/86827_upload_512x512.png";
    const archivedImage = "https://www.shareicon.net/data/512x512/2015/12/27/693999_package_512x512.png";
    const editImage = "https://www.shareicon.net/data/512x512/2017/04/22/884938_edit_512x512.png";
    const deleteImage = "https://www.shareicon.net/data/512x512/2016/01/04/698159_trash_512x512.png";


  return (
    <div className="note">
        <img src={noteImage} alt="note icon"/> 
        <div className="note-data">
          <h2>{note.title}</h2>
          <h3>Last edited: {formatDate(note.editedAt)}</h3>
        </div>
        <div className="note-buttons">
          <button className='action-button' onClick={handleArchive}><img src={note.archived ? unarchivedImage : 
          archivedImage} alt="archive icon" /></button>
          <button className='action-button' onClick={handleForm}><img src={editImage} alt="edit icon" /></button>
          <button className='action-button' onClick={handleDelete}><img src={deleteImage} alt="delete icon" /></button>
        </div>

        {showForm && 
        <NoteForm note={note}
        onConfirm={confirmForm} 
        onCancel={cancelForm}/>}

        {showDeleteDialog && 
        <Dialog text= {'Are you sure you want to delete this note?'}
        onConfirm={confirmDelete} 
        onCancel={cancelDelete}/>}
    </div>
  );
}

export default Note;

function formatDate(inputDate) {
  const date = new Date(inputDate);
  const months = [
    'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
  ];

  const day = date.getDate();
  const month = date.getMonth();
  const year = date.getFullYear();
  const formattedDate = `${day}/${months[month]}/${year}`;

  return formattedDate;
}
