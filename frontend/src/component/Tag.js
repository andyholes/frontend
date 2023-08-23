import './Tag.css';

function Tag({name, updateTags}){
    const handleDelete = () => {
        updateTags(name);
    };

    const deleteIcon = "https://www.shareicon.net/data/512x512/2015/09/26/646633_delete_512x512.png";

    return(
        <div className="tag">
            <img className='tag-icon' src='https://svgsilh.com/svg/1260188.svg' alt="tag icon"/>
            <h3>{name}</h3>
            <button onClick={handleDelete}><img src={deleteIcon} alt="delete icon" /></button>
        </div>
    );
}
export default Tag;