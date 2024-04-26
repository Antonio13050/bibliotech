import { Routes, Route } from "react-router-dom";

import Home from "../pages/home/Home";
import About from "../pages/about/About";
import Login from "../pages/login/Login";
import SignUp from "../pages/signup/SignUp";
import Layout from "../components/layout/Layout";

import { PrivateRoutes } from "./PrivateRoutes";
import ForgotPassword from "@/pages/forgot-password/ForgotPassword";

export default function RoutesComponent() {
    return (
        <Routes>
            <Route
                path="/"
                element={
                    <PrivateRoutes>
                        <Layout />
                    </PrivateRoutes>
                }
            >
                <Route index element={<Home />} />
                <Route path="home" element={<Home />} />
                <Route path="about" element={<About />} />
                <Route path="*" element={<h1>Page not found</h1>} />
            </Route>
            <Route path="login" element={<Login />} />
            <Route path="signup" element={<SignUp />} />
            <Route path="forgot-password" element={<ForgotPassword />} />
            <Route path="*" element={<h1>Page not found</h1>} />
        </Routes>
    );
}
