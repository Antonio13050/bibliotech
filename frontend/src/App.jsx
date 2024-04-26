import "./App.css";
import { ThemeProvider } from "./components/theme-provider";
import { BrowserRouter as Router } from "react-router-dom";
import RoutesComponent from "./routes/Routes";
import { AuthProvider } from "./context/AuthProvider";
import { Toaster } from "@/components/ui/sonner";

function App() {
    return (
        <>
            <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
                <Toaster />
                <AuthProvider>
                    <Router className="grid-container">
                        <RoutesComponent />
                    </Router>
                </AuthProvider>
            </ThemeProvider>
        </>
    );
}

export default App;
