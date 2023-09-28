
let data = undefined


function showResult() {
  
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

  card.classList.add('card')
  
  card.onclick = async () => {
    const content = await getFileContent();
    console.log(content)
    
    fetch('/sort', {
      method: "POST",
      body: {
        algo: details.name, 
        data: content
      }
    }).then(response => {
      response.json(result => {
        showResponse(result)
      })
    })
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
}

showAlgorithmDetails()