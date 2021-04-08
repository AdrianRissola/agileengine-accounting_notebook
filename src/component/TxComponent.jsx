import React, { Component } from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import AccountDataService from '../service/AccountDataService';


class TxComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            message: 'No operation was performed'
        }
    }

    render() {
        return (
            <Formik
                initialValues={{
                    type: '',
                    amount: ''
                }}

                validationSchema={Yup.object().shape({
                    type: Yup.string()//.required("tipo requerido")
                        .matches(/[A-Z]/, 'DEBIT or CREDIT Type is required')
                        .oneOf(['DEBIT', 'CREDIT']),
                    amount: Yup.number()//.required("amount requerido")
                        .positive()
                }).test('at-least-one-property', "you must provide at least one", value =>
                !!(value.type || value.amount)
                )}



                onSubmit={fields => {
                    AccountDataService.commitTx(JSON.stringify(fields, null, 4))
                    .then(
                        response => {
                            console.log(response);
                            this.setState({ message: response.data.status.message })
                        }
                    )
                }}

                render={({ errors, status, touched }) => (
                    <Form>
                        <div className="form-group">
                            <label htmlFor="type">Type</label>
                            <Field component="select" name="type" className={'form-control' + (errors.type && touched.type ? ' is-invalid' : '')} 
                                placeholder="select Type">
                                <option defaultValue>Select DEBIT or CREDIT</option>
                                <option value="DEBIT">DEBIT</option>
                                <option value="CREDIT">CREDIT</option>
                            </Field>
                        </div> 
   
                        <div className="form-group">
                            <label htmlFor="amount">Amount</label>
                            <Field name="amount" type="number" className={'form-control' + (errors.amount && touched.amount ? ' is-invalid' : '')} />
                            <ErrorMessage name="amount" component="div" className="invalid-feedback" />
                        </div>

                        <div className="form-group">
                            <button onClick={this.props.history.goBack} className="btn btn-primary mr-2">Back</button>
                        </div>
                        {
                         ((touched.type||touched.amount) && 
                           !!errors)?
                        <div className="at-least-one-property">sdasd</div>
                        : null
                        }
                        <div className="form-group">
                            <button type="submit" className="btn btn-primary mr-2">Commit Transaction</button>
                        </div>


      
                    <table>
                        <tbody>
                            <tr>
                                <td>{this.state.message}</td>
                            </tr>
                        </tbody>
                    </table>
                    </Form>

                    
                )}
            />
        )
    }
}

export default TxComponent