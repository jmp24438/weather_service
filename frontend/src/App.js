import './App.css';

function App() {
  const setDefaultCoordinates = (city) => {
    const coordinates = {
      Charlotte: { latitude: "35.2271", longitude: "-80.8431" },
      Concord: { latitude: "35.4088", longitude: "-80.5795" },
      Gastonia: { latitude: "35.2621", longitude: "-81.1873" },
      RockHill: { latitude: "34.9249", longitude: "-81.0251" },
      Huntersville: { latitude: "35.4107", longitude: "-80.8428" },
    };
    document.getElementById('latitude').value = coordinates[city].latitude;
    document.getElementById('longitude').value = coordinates[city].longitude;
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Weather Forecast</h1>
        <form action="/getWeather" method="post">
          <label htmlFor="latitude">Latitude:</label>
          <input type="text" id="latitude" name="latitude" required />
          <br />
          <label htmlFor="longitude">Longitude:</label>
          <input type="text" id="longitude" name="longitude" required />
          <br />
          <button type="submit">Get Weather Forecast</button>
          <br /><br />
          <button type="button" onClick={() => setDefaultCoordinates('Charlotte')}>Charlotte</button>
          <button type="button" onClick={() => setDefaultCoordinates('Concord')}>Concord</button>
          <button type="button" onClick={() => setDefaultCoordinates('Gastonia')}>Gastonia</button>
          <button type="button" onClick={() => setDefaultCoordinates('RockHill')}>Rock Hill</button>
          <button type="button" onClick={() => setDefaultCoordinates('Huntersville')}>Huntersville</button>
        </form>
      </header>
    </div>
  );
}

export default App;