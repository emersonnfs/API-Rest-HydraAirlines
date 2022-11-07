import React from "react";
import { Link } from "react-router-dom";

export default function Menu() {
    return (
        <header>
            <Link to="/">Home || </Link>
            <Link to="/cliente">Clientes || </Link>
            <Link to="/cadastrar">Cadstrar || </Link>
        </header>
    );
    }