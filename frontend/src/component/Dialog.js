import './Dialog.css';

function Dialog({text, onConfirm, onCancel }) {

    return (
      <div className="modal-overlay" onClick={onCancel}>
        <div className="dialog-box" onClick={e => e.stopPropagation()}>
          <h2>{text}</h2>
          <button onClick={onCancel}>No</button>
          <button onClick={onConfirm}>Yes</button>
        </div>
      </div>
    );
  }

export default Dialog;