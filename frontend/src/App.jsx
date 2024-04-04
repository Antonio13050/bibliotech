import "./App.css";
import { BrowserRouter as Router } from "react-router-dom";
import RoutesComponent from "./routes/Routes";
import { AuthProvider } from "./context/AuthProvider";

function App() {
    return (
        <>
            <AuthProvider>
                <Router className="grid-container">
                    <RoutesComponent />
                </Router>
            </AuthProvider>
        </>
    );
}

export default App;
