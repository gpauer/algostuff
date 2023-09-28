function showResult(result) {

  const algo = result.algo;
  const time = result.timeInMillis;
  const data = result.data;
  const comp = result.timeComplexity;

  let result_card = document.createElement('article');
  let title = document.createElement('strong');
  let time_p = document.createElement('p');
  let algo_p = document.createElement('p');
  let save_button = document.createElement('button');
  let data_ta = document.createElement('textarea')
  let complexity_p = document.createElement('p')

  time_p.innerText = `Time Spent: ${time}ms`;
  algo_p.innerText = `Algorithm Used: ${algo}`;
  title.innerText = "Sorting Complete";
  data_ta.innerText = data;
  complexity_p.innerText = comp;

  result_card.appendChild(title);
  result_card.appendChild(algo_p);  
  result_card.appendChild(complexity_p);  
  result_card.appendChild(time_p);
  result_card.appendChild(data_ta);


  result_card.classList.add('card')
  console.log(document.getElementById('result-section'))
  document.getElementById('result-section').appendChild(result_card)
}

async function getFileContent() {
  var file = document.getElementById("upload").files[0];
  console.log(file)
  if (file) {
    var reader = new FileReader();
    reader.readAsText(file, "UTF-8");
    await new Promise(resolve => reader.onload = () => resolve());
    let content = reader.result
    return content;
  } else {
    console.log('no such file');
    throw new Error("no such file")
  }
}

function algo_card(details) {
  let card = document.createElement('article');
  let title = document.createElement('strong');
  let desc = document.createElement('p');

  card.classList.add('card');
  card.id = details.name;
  
  card.onclick = async () => {
    try {
      const content = await getFileContent();
      console.log(content)
      
      fetch('/sort', {
        method: "POST",
        headers: new Headers({'content-type': 'application/json'}),
        body: JSON.stringify({
          algo: details.name, 
          data: content.split(',')
        }),
      }).then(response => response.json()).then(result => showResult(result))
    } catch(error) {
      console.log(error)
      alert('choose a valid file');
    }
  }

  title.innerText = details.name;
  desc.innerText = details.description;

  card.appendChild(title)
  card.appendChild(desc)

  return card;
}

const getAlgorithmDetails = () => {
  return fetch('/algo_config.json')
}

const showAlgorithmDetails = async () => {
  const detailList = await (await getAlgorithmDetails()).json();
  const listSection = document.getElementById('algo-list');

  for(let algo of detailList) {
    listSection.appendChild(algo_card(algo))
  }

  document.getElementById('document')

}

showAlgorithmDetails()

console.log(document.getElementById('upload'))
const file_input = document.getElementById('upload')
file_input.onchange = () => {
  document.getElementById('file-name').innerText = `File Selected: ${file_input.value}`
}