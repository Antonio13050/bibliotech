import "./App.css";
import { BrowserRouter as Router } from "react-router-dom";
import RoutesComponent from "./routes/Routes";

function App() {
    return (
        <>
            <Router className="grid-container">
                <RoutesComponent />
            </Router>
        </>
    );
}

export default App;
