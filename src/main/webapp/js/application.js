let interestData = ['BTC', 'ETH', 'LTC', 'NEO', 'ZEC', 'BNC', 'DOGE', 'XMR', 'TRX', 'EOS', 'ICX', 'XEM', 'XRP', 'IOTA', 'BCH', 'ADA'];
let crypto = ['BTC', 'USD', 'ETH'];
let i = 0;
let k = 0;
let orders = [];

function getCurrency() {
    interestData.forEach((el) => {
        for (let i in crypto) {
            $.get(`https://min-api.cryptocompare.com/data/price?fsym=${el}&tsyms=${crypto[i]}`, function (res) {
                $(`#${el.toLocaleLowerCase()}-to-${crypto[i].toLocaleLowerCase()}`).html(Object.values(res));
            });
        }
    });
}

function fadeAllIn() {
    let e = document.getElementById("sel");
    let strUser = e.options[e.selectedIndex].text;
    interestData.forEach((el) => {
        for (let i in crypto) {
            $(`#${el.toLocaleLowerCase()}-to-${strUser.toLocaleLowerCase()}`).parent().fadeIn();
        }
    });
}

function filter() {
    let from = document.getElementById(`from`).value;
    let to = document.getElementById(`to`).value;
    let e = document.getElementById("sel");
    let strUser = e.options[e.selectedIndex].text;
    interestData.forEach((el) => {
        if (!(parseFloat($(`#${el.toLocaleLowerCase()}-to-${strUser.toLocaleLowerCase()}`).html())>=from &&
                parseFloat($(`#${el.toLocaleLowerCase()}-to-${strUser.toLocaleLowerCase()}`).html()) <= to)) {
            $(`#${el.toLocaleLowerCase()}-to-${strUser.toLocaleLowerCase()}`).parent().fadeOut();
        } else {
            $(`#${el.toLocaleLowerCase()}-to-${strUser.toLocaleLowerCase()}`).parent().fadeIn();
        }
    });
}

function createTable() {
    let $tableBody = $('tbody');
    let tr1 = document.createElement('TR');
    tr1.append(document.createElement('TH'));
    for (let el in crypto) {
        let tempTD = document.createElement('TH');
        tempTD.innerText = crypto[el];
        tempTD.id = crypto[el].toLocaleLowerCase();
        tr1.append(tempTD);
    }
    $tableBody.append(tr1);
    interestData.forEach((el) => {
        let tr = document.createElement('TR');
        tr.innerHTML = `<td>${el}</td>`;
        for (let j in crypto) {
            let tempTD = document.createElement('TD');
            tempTD.id = `${el.toLocaleLowerCase()}-to-${crypto[j].toLocaleLowerCase()}`;
            tr.append(tempTD);
        }
        $tableBody.append(tr);
    });
    getCurrency();
}

function findCrypto(crypt) {
    $.get(`https://min-api.cryptocompare.com/data/price?fsym=${crypt}&tsyms=USD`, function (data) {
        let cName = crypt;
        let cVal = data['USD'];
        document.getElementsByName(`curName${cName}`)[0].textContent = cVal;
    });
    orders[i] = crypt;
    i++;
}

function findCryptoOrder(crypt, id) {
    $.get(`https://min-api.cryptocompare.com/data/price?fsym=${crypt}&tsyms=USD`, function (data) {
        let cName = crypt;
        let cVal = data['USD'];
        document.getElementsByName(`curName${cName}${id}`)[0].textContent = cVal;
    });
    orders[k] = crypt;
    k++;
}

function calculateCost(i) {
    let result = 0;
    let amount;
    let cost = 0;
    for (let j = 0; j < i; j++) {
        amount = document.getElementById(`amount${orders[j]}`).value;
        cost = document.getElementById(`curName${orders[j]}`).textContent;
        result += amount * cost;
    }
    document.getElementById(`result`).textContent = result.toFixed(2);
}

function calculateCostOrder(id) {
    let amount;
    let cost;
    amount = document.getElementById(`amount` + id).value;
    console.log(amount);
    cost = document.getElementById(`curName` + id).textContent;
    console.log(cost);
    document.getElementById(`price` + id).textContent = (amount * cost).toFixed(2);
    console.log(document.getElementById(`price` + id).textContent);
}

function calculateCostOrderSell(id) {
    let amount;
    let cost;
    amount = document.getElementById(`amount` + id).textContent;
    cost = document.getElementById(`curName` + id).textContent;
    document.getElementById(`price` + id).textContent = (amount * cost).toFixed(2);
}


