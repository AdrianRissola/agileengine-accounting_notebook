import React, { Component } from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import MeetupDataService from '../service/MeetupDataService';
// import DateFnUtils from '@date-io/date-fns';
// import { MuiPickersUtilsProvider } from 'material-ui/pickers';
// import { KeyboeardDatePicker } from '@material-ui/pickers'

class NewMeetupComponent extends Component {

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
                    title: '',
                    description: '',
                    date: '',
                    maxVacancy: ''
                }}

                validationSchema={Yup.object().shape({
                    title: Yup.string().required("No title provided."),
                    description: Yup.string().required("No description provided."),
                    date: Yup.string().required("No date provided."),
                    maxVacancy: Yup.string().required("No Max Vacancy provided.")
                }).test('at-least-one-property', "you must provide at least one", value =>
                !!(value.type || value.amount)
                )}



                onSubmit={fields => {
                    MeetupDataService.createMeetup(JSON.stringify(fields, null, 4))
                    .then(
                        response => {
                            console.log(response);
                            this.setState({ message: response.data.status.message })
                        }
                    )
                }}

                render={({ errors, status, touched }) => (
                    <Form>
                        <h3 style={{color: "orange"}}>New meetup</h3>

                        <div className="form-group">
                            <label htmlFor="title">Title</label>
                            <Field name="title" type="text" className={'form-control' + (errors.title && touched.title ? ' is-invalid' : '')} />
                            <ErrorMessage name="title" component="div" className="invalid-feedback" />
                        </div>

                        <div className="form-group">
                            <label htmlFor="description">Description</label>
                            <Field name="description" type="text" className={'form-control' + (errors.description && touched.description ? ' is-invalid' : '')} />
                            <ErrorMessage name="description" component="div" className="invalid-feedback" />
                        </div>

                        <div className="form-group">
                            <label htmlFor="date">Date</label>
                            <Field name="date" type="text" className={'form-control' + (errors.date && touched.date ? ' is-invalid' : '')} />
                            <ErrorMessage name="date" component="div" className="invalid-feedback" />
                        </div>
   
                        <div className="form-group">
                            <label htmlFor="maxVacancy">Max Vacancy</label>
                            <Field name="maxVacancy" type="number" className={'form-control' + (errors.maxVacancy && touched.maxVacancy ? ' is-invalid' : '')} />
                            <ErrorMessage name="maxVacancy" component="div" className="invalid-feedback" />
                        </div>

                        <div className="form-group">
                            <button onClick={this.props.history.goBack} className="btn btn-primary mr-2">Back</button>
                            {localStorage.getItem('isAdmin')== 'true'?
                                <button type="submit" className="btn btn-success">Create</button>
                                :null
                            }
                        </div>

                        {
                         ((touched.type||touched.amount) && !!errors)?
                        <div className="at-least-one-property">sdasd</div>
                        : null
                        }


                    </Form>

                    
                )}
            />
        )
    }
}

export default NewMeetupComponent