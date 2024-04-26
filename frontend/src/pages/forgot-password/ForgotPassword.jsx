import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { loginUser } from "../../service/ApiFunctions";
import { useAuth } from "../../context/AuthProvider";
import { useNavigate, useLocation, Link } from "react-router-dom";

import { Button } from "@/components/ui/button";
import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { PasswordInput } from "@/components/password-input";

const formForgotPasswordSchema = z.object({
    email: z
        .string()
        .min(1, { message: "Digite o e-mail" })
        .email({ message: "E-mail inválido" }),
});

export default function ForgotPassword() {
    const form = useForm({
        resolver: zodResolver(formForgotPasswordSchema),
        defaultValues: {
            email: "",
        },
    });

    const [errorMessage, setErrorMessage] = useState("");

    const { handleLogin } = useAuth();
    const navigate = useNavigate();
    const location = useLocation();
    const redirectUrl = location.state?.path || "/";

    const onSubmit = async (data) => {
        console.log("recuperar senha");
    };

    return (
        <div className="h-screen w-screen">
            <div className="container h-screen w-screen mx-auto flex flex-col items-center justify-center">
                <div className="form-container border-2 p-8 rounded-lg lg:w-4/12">
                    <Form {...form}>
                        <form
                            onSubmit={form.handleSubmit(onSubmit)}
                            className="space-y-8"
                        >
                            <h1 className="text-3xl font-bold text-center">
                                Recuperação de senha
                            </h1>
                            <FormField
                                control={form.control}
                                name="email"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>E-mail</FormLabel>
                                        <FormControl>
                                            <Input
                                                placeholder="Digite seu e-mail"
                                                {...field}
                                            />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <div className="text-center">
                                {errorMessage && (
                                    <FormMessage>{errorMessage}</FormMessage>
                                )}
                            </div>

                            <div className="flex justify-center w-full">
                                <Button type="submit" className="sm:w-4/12">
                                    Continuar
                                </Button>
                            </div>
                        </form>
                    </Form>

                    <p className="text-center py-4">
                        <Link to={"/login"} className="hover:underline">
                            Voltar para o login
                        </Link>
                    </p>

                    <p className="text-center">
                        Não tem uma conta?
                        <Link to={"/signup"} className="pl-2 hover:underline">
                            Clique aqui
                        </Link>
                    </p>
                </div>
            </div>
        </div>
    );
}
