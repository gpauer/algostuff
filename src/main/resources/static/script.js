
let data = undefined

function algo_card(details) {
  let card = document.createElement('article');
  let title = document.createElement('strong');
  let desc = document.createElement('p');

  card.classList.add('card')
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
  const detailList = await (await getAlgorithmDetails()).json();
  const listSection = document.getElementById('algo-list');

  for(let algo of detailList) {
    listSection.appendChild(algo_card(algo))
  }
}

showAlgorithmDetails()