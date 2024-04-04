import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthProvider";
import { useNavigate } from "react-router-dom";

export default function Header() {
    const auth = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        auth.handleLogout();
        navigate("/login", {
            state: { message: " You have been logged out!" },
        });
    };

    return (
        <div className="header">
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/about">About</Link>
                </li>
                <li>
                    <Link to="/login">SignIn</Link>
                </li>
                <li>
                    <Link to="/signup">SignUp</Link>
                </li>
            </ul>
            <button onClick={handleLogout}>Logout</button>
        </div>
    );
}
