import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import ListTxsComponent from './ListTxsComponent';
import TxComponent from './TxComponent';



class AccountSystemApp extends Component {
    render() {
        return (
            <Router>
                <>
                    <h1>Account System Application</h1>
                    <Switch>
                        <Route path="/" exact component={ListTxsComponent} />
                        <Route path="/txs" exact component={ListTxsComponent} />
                        <Route path="/txs/newTx" component={TxComponent} />
                    </Switch>
                </>
            </Router>
      )
    }
}

export default AccountSystemApp