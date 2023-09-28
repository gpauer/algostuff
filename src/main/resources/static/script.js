
let data = undefined

function algo_card(details) {
  card = document.createElement('lo');
  title = document.createElement('strong');
  desc = document.createElement('p');

  card.onclick = () => {
    console.log('use: ', details.route)
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
  detailList = await (await getAlgorithmDetails()).json();
  listSection = document.getElementById('algo-list');

  for(algo of detailList) {
    listSection.appendChild(new algo_card(algo))

  }
}

showAlgorithmDetails()