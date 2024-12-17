import axios from "axios";
import { useState } from "react"

const WeatherApp = () =>{
    const[city, setCity] = useState("");
    // const[weather, setWeather] = useState(null);
    const [forecast, setForecast] = useState([]);

    const fetchWeather = async () => {
        try{
            const response = await axios.get(`http://localhost:8080/weather/full?city=${city}`);
            setForecast(response.data);
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
                forecast.length > 0 && (
                    <div>
                        <h2>Full day Weather forecasr for {city}</h2>
                        <ul>
                            {forecast.map((item, index) => (
                                <li key={index}>
                                    <strong>{item.dateTime}</strong>: {item.temperature}°C, {item.description}, Humidity: {item.humidity}%
                                </li>
                            ))}
                        </ul>
                    </div>
                ) 
            }
        </div>
    );
};
export default WeatherApp;