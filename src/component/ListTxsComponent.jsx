import React, { Component } from 'react';
import AccountDataService from '../service/AccountDataService';

class ListTxsComponent extends Component {



    constructor(props) {
        console.log('init');
        super(props)
        this.state = {
            txs: [],
            messageTx: 'No operation was performed',
            balance: null,
            messageBalance: 'No operation was performed'
        }
        this.refreshBalance = this.refreshBalance.bind(this)
        this.refreshTxs = this.refreshTxs.bind(this)
        this.addTxClicked = this.addTxClicked.bind(this)
    }

    addTxClicked() {
        this.props.history.push(`/txs/newTx`)
    }

    componentDidMount() {
        this.refreshTxs();
        this.refreshBalance();
    }

    refreshTxs() {
        AccountDataService.retrieveAllTxs()
            .then(
                response => {
                    console.log(response);
                    this.setState({ txs: response.data.response })
                    this.setState({ messageTx: response.data.status.message })
                }
            )
    }

    
    refreshBalance() {
        AccountDataService.retrieveBalance()
            .then(
                response => {
                    console.log(response);
                    this.setState({ balance: response.data.response })
                    this.setState({ messageBalance: response.data.status.message })
                }
            )
    }

    
    render() {
        return (
            <div className="container">
                <h3 style={{color: "purple"}}>All Transactions</h3>
                <table>
                    <tbody>
                        <tr>
                            <td>Retrieving all transactions:</td>
                            <td>{this.state.messageTx}</td>
                        </tr>
                    </tbody>
                </table>


                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Transaction Id</th>
                                <th>Amount</th>
                                <th>Type</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.txs.map(
                                    tx =>
                                        <tr style={{color: tx.type === "CREDIT" ? "red" : "green"}} 
                                            key={tx.id}>
                                            <td>{tx.id}</td>
                                            <td>{tx.amount}</td>
                                            <td>{tx.type}</td>
                                            <td>{tx.effectiveDate}</td>
                                            
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>

                    <table>
                        <tbody>
                            <tr>
                                <td>Retrieving balance:</td>
                                <td>{this.state.messageBalance}</td>
                            </tr>
                            <tr>
                                <th>Balance:</th>
                                <th>{this.state.balance}</th>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div className="row">
                    <button className="btn btn-success" onClick={this.addTxClicked}>New Transaction</button>
                </div>


            </div>

            
            
        )
    }

}

export default ListTxsComponent
