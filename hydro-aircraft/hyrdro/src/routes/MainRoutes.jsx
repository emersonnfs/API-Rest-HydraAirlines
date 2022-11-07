import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../components/Home";
import Clientes from "../components/Clientes"
import Cadastrar from "../components/Cadastrar"

export default function MainRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/cliente" element={<Clientes />}/>
            <Route path="/cadastrar" element={<Cadastrar />}/>
        </Routes>
    );
}