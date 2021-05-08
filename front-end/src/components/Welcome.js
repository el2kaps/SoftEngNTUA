import React, {Component} from 'react'
import { withRouter } from "react-router-dom";
import { UserConsumer } from '../UserContext';
import {Login} from '../Auth'
import {Table} from 'react-bootstrap'
//import {WelcomeImage} from 'WelcomeImage'
import evImg from "./evImg.png";


export class Welcome extends Component {
    render() {
        console.log("Welcome Props",this.props)
        return (
            <div className="container">
                <Table >
                    <tbody>
                        <tr>
                            <UserConsumer>
                                { context =>
                                        <React.Fragment>
                                            <td>
                                                <h1>Welcome {context.username} !</h1>
                                                        <img src={evImg} width="400" height="300" alt='website logo'/>
                                            </td>
                                            {context.username?null:<td><Login navProps = {this.props}/></td>}
                                        </React.Fragment>
                                    }
                            </UserConsumer>
                        </tr>
                </tbody>
            </Table>
          </div>
        )
    }
}

export default withRouter(Welcome)
