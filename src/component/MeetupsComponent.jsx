import React, { Component, Fragment } from 'react';
import MeetupDataService from '../service/MeetupDataService';

class MeetupsComponent extends Component {



    constructor(props) {
        console.log('init');
        super(props)
        this.state = {
            meetups: [],
            messageResponse: 'No operation was performed',
        }
        this.subscribe = this.subscribe.bind(this)
        this.refreshMeetups = this.refreshMeetups.bind(this)
        this.addNewMeetup = this.addNewMeetup.bind(this)

        //mock
        this.state.meetups = [
            {id:"1", title:"Welcome !!!", description:"Welcome to the First meetup",
            location:"CABA", date:"10/10/2121", subscribed:false, vacancyAvailable:2, 
            subscribedPeople: 100, beersToBuy:1000 },
            {id:"2", title:"Welcome Again", description:"Welcome Again: Second meetup",
            location:"Belgrano", date:"11/11/2121", subscribed:true, vacancyAvailable:3,
            subscribedPeople: 300, beersToBuy:300 },
            {id:"3", title:"IMA Meetup", description:"Indian Mock Ale",
            location:"San Telmo", date:"11/11/2121", subscribed:false, vacancyAvailable:3,
            subscribedPeople: 990, beersToBuy:500 },
            {id:"4", title:"Welcome to AMA", description:"American Mock Ale",
            location:"Avellaneda", date:"11/12/2121", subscribed:false, vacancyAvailable:0,
            subscribedPeople: 870, beersToBuy:900 }
        ]
        console.log(this.state.meetups)
    }

    subscribe(meetupId) {
        MeetupDataService.subscribe(meetupId)
        .then(
            response => {
                console.log(response);
            }
        )
    }

    addNewMeetup(){
        this.props.history.push(`/happybeermeetups/meetup`)
    }

    componentDidMount() {
        this.refreshMeetups();
    }

    refreshMeetups() {
        MeetupDataService.retrieveMeetups()
            .then(
                response => {
                    console.log(response);
                    this.setState({ meetups: response.data.response })
                    this.setState({ messageResponse: response.data.status.message })
                }
            )
    }


    
    render() {
        return (
            <div className="container">
                <h2 style={{color: "green"}}>Welcome {localStorage.getItem('isAdmin')== 'true'?"Admin":null} {localStorage.getItem('user')}</h2>
                <h3 style={{color: "orange"}}>All meetings</h3>
                <table>
                    <tbody>
                        <tr>
                            <td>Meetings found:</td>
                            <td>{this.state.meetups.length}</td>
                        </tr>
                    </tbody>
                </table>

                <div className="container">
                    <table className="table" style={{width: "1100px"}}>
                        <thead>
                            <tr>
                                <th>Meeting Id</th>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Location</th>
                                <th>Date</th>
                                <th>Vacancy Available</th>
                                <th>Subscribed</th>
                                {
                                    localStorage.getItem('isAdmin')== 'true'?
                                    <Fragment>
                                        <th>Subscribed People</th>
                                        <th>Beers to buy</th>
                                    </Fragment>
                                    :null
                                }
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.meetups.map(
                                    meetup =>
                                        <tr style={{color: meetup.subscribed ? "green" : "red"}} 
                                            key={meetup.id}>
                                            <td>{meetup.id}</td>
                                            <td>{meetup.title}</td>
                                            <td>{meetup.description}</td>
                                            <td>{meetup.location}</td>
                                            <td>{meetup.date}</td>
                                            <td>{meetup.vacancyAvailable}</td>
                                            <td>{meetup.subscribed.toString()}</td>
                                            { localStorage.getItem('isAdmin')== 'true'?
                                                <Fragment>
                                                    <td>{meetup.subscribedPeople}</td>
                                                    <td>{meetup.beersToBuy}</td>
                                                </Fragment>
                                                :null
                                                
                                            }
                                            { ((!meetup.subscribed) && meetup.vacancyAvailable>1)?
                                                    <td>
                                                        <div className="row">
                                                            <button className="btn btn-success" onClick={this.subscribe(meetup.id)}>Subscribe</button>
                                                        </div>
                                                    </td>
                                                :null
                                            }
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>

                </div>
                {
                    <div className="row">
                        <button onClick={this.props.history.goBack} className="btn btn-primary mr-2">Back</button>
                        {localStorage.getItem('isAdmin')== 'true'?
                            <button className="btn btn-success" onClick={this.addNewMeetup}>New Beer Meetup</button>
                            :null
                        }
                    </div>
                }

            </div>
  
        )
    }

}

export default MeetupsComponent
