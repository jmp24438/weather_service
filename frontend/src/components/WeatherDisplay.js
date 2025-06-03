import React from 'react';
import './WeatherDisplay.css';

function WeatherDisplay({ location, tableHtml, plotHtml }) {
  return (
    <div className="WeatherDisplay">
      <h1>Weather Data for {location}</h1>
      <div>
        <h2>Data Table</h2>
        <div dangerouslySetInnerHTML={{ __html: tableHtml }} />
      </div>
      <div>
        <h2>Plot</h2>
        <div dangerouslySetInnerHTML={{ __html: plotHtml }} />
      </div>
    </div>
  );
}

export default WeatherDisplay;