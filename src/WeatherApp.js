import axios from "axios";
import { useState } from "react"

const Weather = () =>{
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
}
return (
    <div style={{padding: "20px", fontFamily:"Arial"}}>
        <h1>
            Weather App
        </h1>
        <input type="text">
        </input>
    </div>
)