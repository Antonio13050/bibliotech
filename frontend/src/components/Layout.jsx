import React from "react";
import { Outlet } from "react-router-dom";
import Header from "./Header";
import SideBar from "./SideBar";

export default function Layout() {
    return (
        <div className="grid-container">
            <Header />
            <SideBar />
            <Outlet />
        </div>
    );
}
