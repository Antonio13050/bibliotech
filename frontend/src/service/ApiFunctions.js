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
        return null;
    }
}

export async function registerUser(registration) {
    try {
        const response = await api.post("/users", registration);
        return response;
    } catch (error) {
        if (error.response && error.response.data) {
            throw new Error(error.response.data.detail);
        } else {
            throw new Error(`User registration error : ${error.message}`);
        }
    }
}

export async function verifyEmailAlreadyExists(email) {
    try {
        const response = await api.head(`users/check-email/${email}`);
        if (response.status === 200) {
            return true;
        } else {
            return false;
        }
    } catch (error) {
        if (error.response && error.response.status === 409) {
            return false;
        } else {
            return error;
        }
    }
}

export async function verifyUsernameAlreadyExists(username) {
    try {
        const response = await api.head(`users/check-username/${username}`);
        if (response.status === 200) {
            return true;
        } else {
            return false;
        }
    } catch (error) {
        if (error.response && error.response.status === 409) {
            return false;
        } else {
            return error;
        }
    }
}
