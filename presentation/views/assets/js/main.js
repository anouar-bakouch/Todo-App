//imports
import {Todo} from '../models/Todo.js';

// elements

const nomTodoInput = document.getElementById('nomTodoInput');
const descriptionTodoInput = document.getElementById('descriptionTodoInput');
const addTodoBtn = document.getElementById('addTodoBtn');
const tbody_table = document.getElementById('tbody');
const searchTodoInput = document.getElementById('searchTodoInput');
//urls

const url_get = 'http://localhost:8080/todo/listTodo';
const url_post = 'http://localhost:8080/todo/addTodo';
const url_delete = 'http://localhost:8080/todo/deleteTodo';
const url_put = 'http://localhost:8080/todo/updateTodo';

const addToTable = (todo)=>{

    //creating a row with cols.

    let tr = document.createElement('tr');
    let td_nom = document.createElement('td');
    let td_description = document.createElement('td');
    let td_action = document.createElement('td');

    //assigning ids to tds.

    td_nom.id='td_nom';
    td_description.id='td_description';

    td_action.className = 'd-flex justify-content-between'

    //creating the btns
    let removeTodoBtn = document.createElement('button');
    let updateTodoBtn = document.createElement('button');
    //adding styles.
    removeTodoBtn.className = 'btn btn-primary';
    removeTodoBtn.innerText = 'supprimer';
    removeTodoBtn.id = todo.nom;
    removeTodoBtn.addEventListener('click',async()=>{

        let form = new FormData();
        form.append("nom",todo.nom);

        try{

            const response = await fetch (url_delete,{
                "method" : 'post',
                "body" : form
            });

            const data = await response.json();

            if(data == '1'){

                document.getElementById(todo.nom).parentNode.parentNode.remove();
            }

        }catch(error){
            console.log(error);
        }

    });

    updateTodoBtn.className = 'btn btn-outline-primary';
    updateTodoBtn.id= todo.nom;
    updateTodoBtn.innerText = 'modifier';

    updateTodoBtn.addEventListener('click',async()=>{

    td_nom.innerHTML='<input value="'+td_nom.innerText+'" class="form-control" type="text" />';
    td_description.innerHTML = '<input value="'+td_description.innerText+'" class="form-control" type="text" />';


    })

    //filling the tds.
    td_nom.innerText = todo.nom;
    td_description.innerText = todo.description;
    td_action.appendChild(updateTodoBtn);
    td_action.appendChild(removeTodoBtn);

    //adding the tds to tr and then to the tbody.
    tr.appendChild(td_nom);
    tr.appendChild(td_description);
    tr.appendChild(td_action);

    tbody_table.appendChild(tr);

}

window.addEventListener('load',async ()=>{


    try{

        const response = await fetch (url_get);

        const data = await response.json();

        data.forEach(todo=>{
            let nom = todo.nom;
            let description = todo.description;
            addToTable(new Todo(nom,description));
        })

    }catch(error){
        console.log(error);
    }


})

addTodoBtn.addEventListener('click',async ()=>{

    let nom = nomTodoInput.value;
    let description = descriptionTodoInput.value;
    let todo = new Todo(nom,description);
    let form = new FormData();
    form.append("nom",todo.nom);
    form.append("description",todo.description);
    try{

        const response = await fetch (url_post,{
            "method" : 'post',
            "body" : form
        });

        const data = await response.json();
        if(data == '1'){
             addToTable(todo);
        }

    }catch(error){
        console.log(error);
    }


})

searchTodoInput.addEventListener('input',()=>{

    let searchedValue = searchTodoInput.value;
    for (let i = 0; i < tbody_table.children.length; i++) {
        //tr
        let tr = tbody_table.children[i];
            //td
        // we will choose specific columns.
            let nom = tr.children[0].innerText;
            let description = tr.children[1].innerText;

            if(nom.search(searchedValue.toLowerCase())===-1 && description.search(searchedValue.toLowerCase())===-1 ){
               tr.setAttribute("class","d-none");
            }
            else
                tr.removeAttribute("class");



        }


})



