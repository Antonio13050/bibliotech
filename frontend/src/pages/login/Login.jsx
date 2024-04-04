import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { loginUser } from "../../service/ApiFunctions";
import { useAuth } from "../../context/AuthProvider";
import { useNavigate, useLocation } from "react-router-dom";

const formLoginSchema = z.object({
    email: z.string().min(1, "Digite o e-mail").email("E-mail inválido"),
    password: z.string().min(1, "Digite a senha"),
});

export default function SignIn() {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm({
        resolver: zodResolver(formLoginSchema),
    });

    const [errorMessage, setErrorMessage] = useState("");

    const auth = useAuth();
    const navigate = useNavigate();
    const location = useLocation();
    const redirectUrl = location.state?.path || "/";

    const onSubmit = async (data) => {
        const success = await loginUser(data);
        if (success) {
            const token = success.token;
            auth.handleLogin(token);
            navigate(redirectUrl, { replace: true });
        } else {
            setErrorMessage("Invalid username or password. Please try again.");
        }
        setTimeout(() => {
            setErrorMessage("");
        }, 4000);
    };

    return (
        <div>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label>E-mail</label>
                <input
                    type="email"
                    placeholder="name@example.com"
                    {...register("email")}
                />
                {errors.email && <p>{errors.email.message}</p>}

                <div>
                    <label>Senha</label>
                    <input
                        type="password"
                        placeholder="Senha"
                        {...register("password")}
                    />
                    {errors.password && (
                        <p className="text-danger">{errors.password.message}</p>
                    )}

                    {errorMessage && (
                        <p className="text-danger">{errorMessage}</p>
                    )}
                </div>
                <button type="submit">Entrar</button>
            </form>
        </div>
    );
}
