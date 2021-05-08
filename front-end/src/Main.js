import React, { Component } from 'react';
//import FormDialog from './components/FormDialog';
import Dropdown from './components/Dropdown';
import { UserConsumer } from './UserContext';
import {Table} from 'react-bootstrap'
import MyButton from './components/MyButton';
import ReactDOM from 'react-dom';
import SimplePieChart from './components/SimplePieChart';
import ReactJson from 'react-json-view'
import {JsonToTable} from "react-json-to-table";

//import DatePicker from 'react-datepicker'
//import Container from 'react-bootstrap/Container'

const btnStyle ={
    marginLeft:10,
    width:150,
    background:'#29c596'
}

class Main extends Component {
    constructor(props) {
		super(props)


		this.state = {
            
            type:"",
            point:"",
            car:"",
            station:"",
            provider:"",
            chhistory:"",
            year:"",
            month:"",
            day: "",
            year2:"",
            userbilling:"",
            month2:"",
            day2: "",
            EVHistory:"",
            providerbilling:"",
            options:"",
            evinfo:"",
            duration:"",
            session:""
		}
        //this.callBack = this.callBack.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        //this.fakeSubmit = this.fakeSubmit.bind(this)
        //this.checkValidation = this.checkValidation.bind(this)
    }

    setPoint = (value,label) => {
        //console.log("Area:", childData)
        this.setState({point: label},()=>console.log("Point:",this.state))
    };
    setSession = (value,label) => {
        //console.log("Area:", childData)
        this.setState({session: label},()=>console.log("session:",this.state))
    };
    setproviderbilling = (value,label) => {
        //console.log("Area:", childData)
        this.setState({providerbilling: label},()=>console.log("providerbilling:",this.state))
    };
    setchhistory = (value,label) => {
        //console.log("Area:", childData)
        this.setState({chhistory: label},()=>console.log("Chhistory:",this.state))
    };

    setEVHistory = (value,label) => {
        //console.log("Area:", childData)
        this.setState({EVHistory: label},()=>console.log("EVHistory:",this.state))
    };
    
    setCar = (value,label) => {
        //console.log("Area:", childData)
        this.setState({car: label},()=>console.log("Car:",this.state))
    };  
    setStation = (value,label) => {
        //console.log("Area:", childData)
        this.setState({station: label},()=>console.log("Station:",this.state))
    };

    setProvider = (value,label) => {
        //console.log("Area:", childData)
        this.setState({provider: label},()=>console.log("Provider:",this.state))
    };


    setType = (value,label) => {
        console.log(localStorage.getItem('username'))
        this.setState({type: value},()=>console.log("Type:",this.state))
    };  

   /* setDate(newDate){
        this.setState({date: newDate})
    };*/
    setYear = (value,label) => {
        //console.log("Year:", childData)
        this.setState({year: label},()=>console.log("Year:",this.state))
    };
    setMonth = (value,label) => {
        //console.log("Month:", childData)
        this.setState({month: value},()=>console.log("Month:",this.state))
    };
    setDay = (value,label) => {
        //console.log("Year:", childData)
        this.setState({day: label},()=>console.log("Day:",this.state))
    };

    setYear2 = (value,label) => {
        //console.log("Year:", childData)
        this.setState({year2: label},()=>console.log("Year2:",this.state))
    };
    setMonth2 = (value,label) => {
        //console.log("Month:", childData)
        this.setState({month2: value},()=>console.log("Month2:",this.state))
    };
    setDay2 = (value,label) => {
        //console.log("Year:", childData)
        this.setState({day2: label},()=>console.log("Day2:",this.state))
    };

    getDaysFromMonts(){
        if(this.state.month===""){
            return "31"
        }
        if(this.state.month==="2"){
            return "29"
        }
        if((this.state.month<8 && this.state.month%2===1)||(this.state.month>=8 && this.state.month%2===0)){
            return "31"
        }
        else{
            return "30"
        }

    }

        getDaysFromMonts2(){
        if(this.state.month2===""){
            return "31"
        }
        if(this.state.month2==="2"){
            return "29"
        }
        if((this.state.month2<8 && this.state.month2%2===1)||(this.state.month2>=8 && this.state.month2%2===0)){
            return "31"
        }
        else{
            return "30"
        }

    }


    handleSubmit() {
        let dateArg = ""
        console.log('Hopfully submiting ', this.state);

      /*  if(!this.checkValidation()){
            alert("Please fill all the requierd fields")
            return
        }
        */
        console.log(localStorage.getItem('token'))

            let url = 'https://localhost:8765/evcharge/api/';
            
            if(this.state.type==="SessionsPerPoint"){
               
                
                url+= this.state.type+"/"+this.state.point+'/'+this.state.year+this.state.month+this.state.day+'/'+this.state.year2+this.state.month2+this.state.day2;
               //url+= this.state.type+"/"+'point_014edacb6c6e4932be710234417730aa'+'/'+this.state.year+this.state.month+'10'+'/'+this.state.year2+this.state.month2+this.state.day2;
            }

            if(this.state.type==="SessionsPerEV"){
                url+= this.state.type+"/"+this.state.car+'/'+this.state.year+this.state.month+this.state.day+'/'+this.state.year2+this.state.month2+this.state.day2;
               //url+= this.state.type+"/"+'point_014edacb6c6e4932be710234417730aa'+'/'+this.state.year+this.state.month+'10'+'/'+this.state.year2+this.state.month2+this.state.day2;
            }

            if(this.state.type==="SessionsPerStation"){
                url+= this.state.type+"/"+this.state.station+'/'+this.state.year+this.state.month+this.state.day+'/'+this.state.year2+this.state.month2+this.state.day2;
               //url+= this.state.type+"/"+'point_014edacb6c6e4932be710234417730aa'+'/'+this.state.year+this.state.month+'10'+'/'+this.state.year2+this.state.month2+this.state.day2;
            }

            if(this.state.type==="SessionsPerProvider"){
                url+= this.state.type+"/"+this.state.provider+'/'+this.state.year+this.state.month+this.state.day+'/'+this.state.year2+this.state.month2+this.state.day2;
               //url+= this.state.type+"/"+'point_014edacb6c6e4932be710234417730aa'+'/'+this.state.year+this.state.month+'10'+'/'+this.state.year2+this.state.month2+this.state.day2;
            }

            if(this.state.type==="EVHistory"){
                url+= "SessionsPerEV/"+this.state.car+'/'+'20001010'+'/'+'20301010';
            }
            
            if(this.state.type==="Chhistory"){
                url+= "chhistory/"+localStorage.getItem('username')+"/"+this.state.car;
            }
            if(this.state.type==="providerbilling"){
                url+= "providerbilling/"+localStorage.getItem('username');
            }
            if(this.state.type==="options"){
                url+= "options/"+localStorage.getItem('username');
            }
            
            if(this.state.type==="userbilling"){
                url+= "userbilling/"+localStorage.getItem('username');
            }
            if(this.state.type==="evinfo"){
                url+= "evinfo/"+localStorage.getItem('username')+"/"+this.state.car;
            }
            if(this.state.type==="duration"){
                url+= "duration/"+localStorage.getItem('username')+"/"+this.state.session;
            }
            

            url += "?type=json"
            console.log("url:",url)
        
        fetch(url,{
            method: 'GET',
            headers: {
                'Content-Type':'application/x-www-form-urlencoded',
                "Token": localStorage.getItem('token')
            }
        }).then((response) =>  response.json())
        .then(json => {    
            
            console.log("IM HERE:",json);
            if(json.length>0){
                //ReactDOM.render(this.createGraph(json,dateArg),document.getElementById('graph')) 
                //ReactDOM.render(<ReactJson src={json} />,document.getElementById('graph'))
                ReactDOM.render(<JsonToTable json={json} />, document.getElementById('graph'))
            }
            else{
                alert("There is no Data for your request")
                ReactDOM.render(<div></div>,document.getElementById('graph'))
            }
            
    

        }); 

    }

    render(){
        return (
            <div >
                <div className='row'>
                    <h1>
                        Main page
                    </h1>                
                </div>
                <div className="row">
                    <UserConsumer>
                        { context =>
                        <Table >
                            <tbody>
                                <tr>
                                    <td> Select Functionality: <Dropdown name="fname" value = {this.setType} type = "Type" />
                                    
                                    {this.state.type==="SessionsPerPoint"?
                                    <Dropdown  value = {this.setPoint} type = "SessionsPerPoint" />
                    
                                    :this.state.type==="SessionsPerEV"?
                                    <Dropdown  value = {this.setCar} type = "SessionsPerEV" />
                                    
                                    :this.state.type==="SessionsPerStation"?
                                    <Dropdown  value = {this.setStation} type = "SessionsPerStation" />

                                    :this.state.type==="SessionsPerProvider"?
                                    <Dropdown  value = {this.setProvider} type = "SessionsPerProvider" />

                                    :this.state.type==="EVHistory"?
                                    <Dropdown  value = {this.setCar} type = "EVHistory" />

                                    :this.state.type==="Chhistory"?
                                    <Dropdown  value = {this.setCar} type = "Chhistory" />

                                    :this.state.type==="evinfo"?
                                    <Dropdown  value = {this.setCar} type = "evinfo" />

                                    :this.state.type==="duration"?
                                    <Dropdown  value = {this.setSession} type = "duration" />


                                    :null}
        
                                    </td>


                                </tr>
                          {/*      <tr> 
                                    <td><MyButton callBack = {this.callBack} select = {this.state.YearSelect} name = 'Year'/></td>
                                    <td><MyButton callBack = {this.callBack} select = {this.state.MonthSelect} name = 'Month'/></td>
                                    <td><MyButton callBack = {this.callBack} select = {this.state.DaySelect} name = 'Day'/></td>

                          </tr>  */}
                                {this.state.type==="SessionsPerPoint"?
                                    <tr>
                                    <td><Dropdown  value = {this.setYear} type = "Year" /></td>
                                    <td><Dropdown   value = {this.setMonth}  type = "Month" /></td>
                                    <td><Dropdown   value = {this.setDay} month = {this.getDaysFromMonts()} type = "Day" /></td>
                                </tr>
                    
                                    :this.state.type==="SessionsPerEV"?
                                    <tr>
                                    <td><Dropdown  value = {this.setYear} type = "Year" /></td>
                                    <td><Dropdown   value = {this.setMonth}  type = "Month" /></td>
                                    <td><Dropdown   value = {this.setDay} month = {this.getDaysFromMonts()} type = "Day" /></td>
                                </tr>
                                    
                                    :this.state.type==="SessionsPerStation"?
                                    <tr>
                                    <td><Dropdown  value = {this.setYear} type = "Year" /></td>
                                    <td><Dropdown   value = {this.setMonth}  type = "Month" /></td>
                                    <td><Dropdown   value = {this.setDay} month = {this.getDaysFromMonts()} type = "Day" /></td>
                                </tr>

                                    :this.state.type==="SessionsPerProvider"?
                                    <tr>
                                    <td><Dropdown  value = {this.setYear} type = "Year" /></td>
                                    <td><Dropdown   value = {this.setMonth}  type = "Month" /></td>
                                    <td><Dropdown   value = {this.setDay} month = {this.getDaysFromMonts()} type = "Day" /></td>
                                </tr>
                                    
                                    :null}
                                {/*<tr>
                                    <td><Dropdown  value = {this.setYear} type = "Year" /></td>
                                    <td><Dropdown   value = {this.setMonth}  type = "Month" /></td>
                                    <td><Dropdown   value = {this.setDay} month = {this.getDaysFromMonts()} type = "Day" /></td>
                                </tr>*/}


                                {this.state.type==="SessionsPerPoint"?
                                    <tr>
                                    <td><Dropdown  value = {this.setYear2} type = "Year2" /></td>
                                    <td><Dropdown   value = {this.setMonth2}  type = "Month2" /></td>
                                    <td><Dropdown   value = {this.setDay2} month = {this.getDaysFromMonts2()} type = "Day2" /></td>
                                </tr>
                    
                                    :this.state.type==="SessionsPerEV"?
                                   <tr>
                                    <td><Dropdown  value = {this.setYear2} type = "Year2" /></td>
                                    <td><Dropdown   value = {this.setMonth2}  type = "Month2" /></td>
                                    <td><Dropdown   value = {this.setDay2} month = {this.getDaysFromMonts2()} type = "Day2" /></td>
                                </tr>
                                    
                                    :this.state.type==="SessionsPerStation"?
                                    <tr>
                                    <td><Dropdown  value = {this.setYear2} type = "Year2" /></td>
                                    <td><Dropdown   value = {this.setMonth2}  type = "Month2" /></td>
                                    <td><Dropdown   value = {this.setDay2} month = {this.getDaysFromMonts2()} type = "Day2" /></td>
                                </tr>

                                    :this.state.type==="SessionsPerProvider"?
                                    <tr>
                                    <td><Dropdown  value = {this.setYear2} type = "Year2" /></td>
                                    <td><Dropdown   value = {this.setMonth2}  type = "Month2" /></td>
                                    <td><Dropdown   value = {this.setDay2} month = {this.getDaysFromMonts2()} type = "Day2" /></td>
                                </tr>
                                    
                                    :null}

                                {/*<tr>
                                    <td><Dropdown  value = {this.setYear2} type = "Year2" /></td>
                                    <td><Dropdown   value = {this.setMonth2}  type = "Month2" /></td>
                                    <td><Dropdown   value = {this.setDay2} month = {this.getDaysFromMonts2()} type = "Day2" /></td>
                                </tr>*/}
                                <tr>
                                    <td></td><td></td>
                                    <td><button onClick = {this.handleSubmit} style = {btnStyle} className="btn btn-info">Submit</button></td>
                                </tr>
                                {/*<tr><td><button onClick = {this.fakeSubmit} style = {btnStyle} className="btn btn-info">Submit</button></td></tr>*/}

                        </tbody>
                    </Table>
                }
                </UserConsumer>

                    
                </div>
                
                <div id="graph"> </div>
            </div>
        );
    }
    
}

export default Main;
