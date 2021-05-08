import React, { Component } from 'react'


class PrettyPrintJson {data} extends React.Component {
    render() {
         // data could be a prop for example
         // const { data } = this.props;
         return (<div><pre>{JSON.stringify(data, null, 2) }</pre></div>);
    }
}