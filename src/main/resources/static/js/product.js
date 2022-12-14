//функция возвращения на предыдущую страницу
document.getElementById('button_back').addEventListener('click', () => {
    history.back();
});

// const back = document.getElementById('back');
// back.onclick = function(){
//     history.back();
// };

function return_back() {   // Функция "return_back" - возвращает на предыдущею страницу
    window.history.back(); // Метод возврата на предыдущею страницу
};

const back = document.getElementById('back');      // Получаем элемент с ID "back"
back.addEventListener('click', return_back);