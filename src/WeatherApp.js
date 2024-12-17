import axios from "axios";
import { useState } from "react"

const WeatherApp = () =>{
    const[city, setCity] = useState("");
    const[weather, setWeather] = useState(null);

    const fetchWeather = async () => {
        try{
            const response = await axios.get(`http://localhost:8080/weather/parsed?city=${city}`);
            setWeather(response.data);
        }
        catch(error){
            alert("City not found or server error.");
        }
    };

    return (
        <div style={{padding: "20px", fontFamily:"Arial"}}>
            <h1>
                Weather App
            </h1>
            <input 
                type="text"
                value={city}
                onChange={(e) => setCity(e.target.value)}
                placeholder="Enter city"
            />
            <button onClick={fetchWeather}>Get Weather</button>
            {
                weather && (
                    <div>
                        <h2>Weather in (weather.city)</h2>
                        <p>Description : (weather.description)</p>
                        <p>Temperature : (weather.temperature) °C</p>
                        <p>Humidity : (weather.humidity)%</p>
                    </div>
                ) 
            }
        </div>
    );
};
export default WeatherApp;