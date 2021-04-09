import axios from 'axios'

const TX = 'getTransactions'
const ACCOUNT_API_URL = 'http://localhost:8080'
const TX_API_URL = `${ACCOUNT_API_URL}/moneyAccountService/${TX}`
const COMMIT_API_URL = `${ACCOUNT_API_URL}/moneyAccountService/commitTransaction/`
const BALANCE_API_URL = `${ACCOUNT_API_URL}/moneyAccountService/getBalance/`




const BASE_API_URL = 'http://localhost:8080/beermeetupmanager/'
const LOGIN = 'login/'
const MEETUPS = `meetups/`


class MeetupDataService {

    
    login(credentials) {
        console.log('executed service login');
        return axios.post(`${BASE_API_URL}${LOGIN}`, credentials, 
        {
            headers: { 'Authorization': 'Basic xxxxxxxxxxxxxxxxxxx',
            'Content-Type' : 'application/json' }
        });
    }

    
    subscribe(meetupId) {
        let userId = localStorage.getItem('userId');
        console.log('executing service subscribe with: ' + meetupId + '' + userId);
        return axios.put(`${BASE_API_URL}${MEETUPS}${meetupId}/${userId}`,
        {
            headers: { 
                'Authorization': 'Basic xxxxxxxxxxxxxxxxxxx',
                'Content-Type' : 'application/json'
            }
        });
    }

    
    retrieveMeetups() {
        console.log('executing service retrieveMeetups');
        return axios.get(`${BASE_API_URL}${MEETUPS}`);
    }

    
    createMeetup(meetup) {
        console.log('executing service subscribe with: ' + meetup);
        return axios.post(`${BASE_API_URL}${MEETUPS}`, meetup,
        {
            headers: { 
                'Authorization': 'Basic xxxxxxxxxxxxxxxxxxx',
                'Content-Type' : 'application/json'
            }
        });
    }







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

export default new MeetupDataService()