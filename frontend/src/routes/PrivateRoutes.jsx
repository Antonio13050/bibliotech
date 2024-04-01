import { Navigate } from "react-router-dom";

export const PrivateRoutes = ({ children }) => {
    const isAuthenticated = true;

    return isAuthenticated ? children : <Navigate to="/login" />;
};
