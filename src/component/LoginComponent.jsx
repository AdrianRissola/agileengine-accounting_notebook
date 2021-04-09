import React, { Component } from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import MeetupDataService from '../service/MeetupDataService';


class NewMeetupComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }

    render() {
        return (
            <Formik
                initialValues={{
                    user: '',
                    password: ''
                }}
                
                validationSchema={
                    Yup.object().shape({
                    user: Yup.string().required('No user provided.'),
                    password: Yup.string().required('No password provided.')
                    })
                }



                onSubmit={fields => {
                    MeetupDataService.login(JSON.stringify(fields, null, 4))
                    .then(
                        response => {
                            console.log(response);
                            this.props.history.push(`/happybeermeetups`)
                            localStorage.setItem('user', fields.user)
                            localStorage.setItem('userId', response.date.userId)
                            localStorage.setItem('isAdmin', response.date.isAdmin)
                        }
                    )
                    //mock
                    localStorage.setItem('user', fields.user)
                    if(fields.user=="adrian")
                        localStorage.setItem('isAdmin', true)
                    else
                        localStorage.setItem('isAdmin', false)

                    this.props.history.push(`/happybeermeetups`)
                }}

                render={({ errors, status, touched }) => (

                    
                    <Form>
                        <h3 style={{color: "orange"}}>Login</h3>
                        <div className="form-group">
                            <label htmlFor="user">User</label>
                            <Field name="user" type="text" style={{width: "250px"}}
                                className={'form-control' + (errors.user && touched.user ? ' is-invalid' : '')} />
                            <ErrorMessage name="user" component="div" className="invalid-feedback" />
                        </div>

                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <Field name="password" type="password" style={{width: "250px"}}
                                className={'form-control' + (errors.password && touched.password ? ' is-invalid' : '')} />
                            <ErrorMessage name="password" component="div" className="invalid-feedback" />
                        </div>


                    <div className="form-group">
                        <button type="submit" className="btn btn-success">Login</button>
                    </div>

      
                    </Form>

                    
                )}
            />
        )
    }
}

export default NewMeetupComponent