import axios from 'axios'

const TX = 'getTransactions'
const ACCOUNT_API_URL = 'http://localhost:8080'
const TX_API_URL = `${ACCOUNT_API_URL}/moneyAccountService/${TX}`
const COMMIT_API_URL = `${ACCOUNT_API_URL}/moneyAccountService/commitTransaction/`
const BALANCE_API_URL = `${ACCOUNT_API_URL}/moneyAccountService/getBalance/`

class AccountDataService {

    retrieveAllTxs(name) {
        console.log('executed service retrieveAllTxs');
        return axios.get(`${TX_API_URL}`);
    }

    retrieveBalance(name) {
        console.log('executed service retrieveBalance');
        return axios.get(`${BALANCE_API_URL}`);
    }

    commitTx(tx) {
        console.log('executed service commitTx');
        return axios.post(`${COMMIT_API_URL}`, tx, 
        {
            headers: { 'Authorization': 'Basic xxxxxxxxxxxxxxxxxxx',
            'Content-Type' : 'application/json' }
        });
    }
}

export default new AccountDataService()