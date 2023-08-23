import { useState } from "react";
import Tag from "./Tag";
import './NoteForm.css';

function NoteForm ({note, onConfirm, onCancel}){
    const [title, setTitle] = useState(note.title);
    const [content, setContent] = useState(note.content);
    const [tags, setTags] = useState(note.tags);
    const [errorMessage, setErrorMessage] = useState();

    const updateTags = (deletedTag) => {
        setTags((tags) => tags.filter((tag) => tag !== deletedTag));
    };

    const addTag = (e) => {
        e.preventDefault();
        const newTag = e.target.form.elements["new-tag"].value.replaceAll(" ", "-");
        if (!newTag || newTag === "") {
            setErrorMessage("Tag cannot be empty!");
          } else if (!tags.includes(newTag)) {
            setTags((tags) => [...tags, newTag]);
            e.target.form.elements["new-tag"].value = "";
            setErrorMessage("");
        } else {
            setErrorMessage("Tag already exists!");
        }
    };

    const handleTitleChange = (e) => {
        setTitle(e.target.value);
      };
    
      const handleContentChange = (e) => {
        setContent(e.target.value);
      };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!title || title ===""){
            setErrorMessage("Title must have at least one character!")
        } else{
        const newNote = {
            id: note.id,
            title: title,
            content: content,
            archived: note.archived,
            tags:tags}
            onConfirm(newNote);
        };
    }

    return (
        <div className="modal-overlay" onClick={onCancel}>
          <div className="note-form" onClick={(e) => e.stopPropagation()}>
            <h1>Create/Edit note</h1>
            <form>
              <div className="attrib">
                <label htmlFor="title">Title</label>
                <input id="title" name="title" value={title} onChange={handleTitleChange} />
              </div>
              <div className="attrib">
                <label htmlFor="content">Content</label>
                <textarea id="content" name="content" value={content} onChange={handleContentChange} />
              </div>
              <label>Categories</label>
              <div className="tags-list">
                {tags.map((tag) => (
                  <Tag key={tag} name={tag} updateTags={updateTags} />
                ))}
              </div>
              <div className="add-tag">
                <input name="new-tag" className="new-tag"/>
                <button className="submit-tag" type="submit" onClick={addTag}>
                  Add
                </button>
              </div>
            <div className="buttons">
            <button type="submit" onClick={handleSubmit}>Save</button>
            <button onClick={onCancel}>Cancel</button>
            </div>
            {errorMessage && <p>* {errorMessage}</p>}
            </form>
          </div>
        </div>
      );
    }

export default NoteForm;