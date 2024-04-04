import axios from "axios";

export const api = axios.create({
    baseURL: "http://localhost:8080",
});

export async function loginUser(login) {
    try {
        const response = await api.post("login", login);
        if (response.status >= 200 && response.status < 300) {
            return response.data;
        } else {
            return null;
        }
    } catch (error) {
        console.error(error);
        return null;
    }
}

export async function registerUser(registration) {
    try {
        const response = await api.post("/users", registration);
        return response.data;
    } catch (error) {
        if (error.response && error.response.data) {
            throw new Error(error.response.data.detail);
        } else {
            throw new Error(`User registration error : ${error.message}`);
        }
    }
}
